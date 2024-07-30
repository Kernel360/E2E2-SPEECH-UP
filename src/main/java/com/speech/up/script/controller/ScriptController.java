package com.speech.up.script.controller;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speech-script")
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptService scriptService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ScriptEntity>> getScriptAll(@PathVariable Long userId){
        return scriptService.getScriptList(userId);
    }
}
