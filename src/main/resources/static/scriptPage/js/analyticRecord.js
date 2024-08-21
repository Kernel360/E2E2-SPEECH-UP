const list = document.getElementById('recordings-list');

// WAV 헤더를 생성하는 함수
function createWavHeader(sampleRate, numChannels, bitsPerSample, dataLength) {
    const header = new ArrayBuffer(44);
    const view = new DataView(header);

    const writeString = (view, offset, string) => {
        for (let i = 0; i < string.length; i++) {
            view.setUint8(offset + i, string.charCodeAt(i));
        }
    };

    // RIFF chunk descriptor
    writeString(view, 0, 'RIFF'); // ChunkID
    view.setUint32(4, 36 + dataLength, true); // ChunkSize (file size - 8)
    writeString(view, 8, 'WAVE'); // Format

    // fmt sub-chunk
    writeString(view, 12, 'fmt '); // Subchunk1ID
    view.setUint32(16, 16, true); // Subchunk1Size (16 for PCM)
    view.setUint16(20, 1, true); // AudioFormat (1 for PCM)
    view.setUint16(22, numChannels, true); // NumChannels
    view.setUint32(24, sampleRate, true); // SampleRate
    view.setUint32(28, sampleRate * numChannels * bitsPerSample / 8, true); // ByteRate
    view.setUint16(32, numChannels * bitsPerSample / 8, true); // BlockAlign
    view.setUint16(34, bitsPerSample, true); // BitsPerSample

    // data sub-chunk
    writeString(view, 36, 'data'); // Subchunk2ID
    view.setUint32(40, dataLength, true); // Subchunk2Size

    return header;
}

function displayRecords(records) {
    list.innerHTML = '';
    records.forEach(record => {
        const pcmData = base64ToUint8Array(record.audio_path);
        if (pcmData) {
            try {
                const wavHeader = createWavHeader(16000, 1, 16, pcmData.length);
                const wavData = new Uint8Array(wavHeader.byteLength + pcmData.byteLength);

                wavData.set(new Uint8Array(wavHeader), 0);
                wavData.set(pcmData, wavHeader.byteLength);

                const blob = new Blob([wavData], { type: 'audio/wav' });
                const url = URL.createObjectURL(blob);

                const li = document.createElement('li');
                li.innerHTML = `
                    <div class="recording-item">
                        <audio controls src="${url}"></audio>
                        <button onclick="navigate('${btoa(JSON.stringify(record))}')">
                            ${record.analyzed ? '분석결과 보러가기' : '분석하기'}
                        </button>
                    </div>
                `;

                list.appendChild(li);

                // URL을 해제하여 메모리 누수 방지
                li.querySelector('audio').onended = () => {
                    URL.revokeObjectURL(url);
                };
            } catch (error) {
                console.error("Error creating audio element: ", error);
            }
        } else {
            console.error("Failed to load audio data for record:", record);
        }
    });
}

async function loadRecords(scriptId) {
    try {
        const response = await fetch(`/speech-record/${scriptId}`);
        if (!response.ok) {
            throw new Error(`Failed to load records: ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error fetching records: ", error);
        return [];
    }
}

const scriptId = new URLSearchParams(window.location.search).get('id');

document.addEventListener('DOMContentLoaded', async () => {
    const records = await loadRecords(scriptId);
    if (records && Array.isArray(records)) {
        displayRecords(records);
    } else {
        console.error("Invalid records format:", records);
    }
});

function base64ToUint8Array(base64) {
    try {
        const binaryString = atob(base64); // base64 문자열을 이진 데이터로 디코딩
        const len = binaryString.length;
        const bytes = new Uint8Array(len);
        for (let i = 0; i < len; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes;
    } catch (error) {
        console.error("Error converting base64 to Uint8Array: ", error);
        return null;
    }
}

function navigate(record) {
    const recordJson = JSON.parse(atob(record));

    if (recordJson.analyzed) {
        window.location.href = `/reports/${recordJson.record_id}`;
    } else {
        saveRecord(recordJson.record_id);
    }
}