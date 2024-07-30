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

    @PostMapping("")
    public ResponseEntity<ScriptEntity> addScript(@RequestBody ScriptEntity scriptEntity) {
        return scriptService.addScript(scriptEntity);
    }

    @PutMapping("")
    public ResponseEntity<ScriptEntity> updateScript(
        @RequestBody ScriptEntity scriptEntity) {
        return scriptService.updateScript(scriptEntity);
    }
    //
    @DeleteMapping("/{scriptId}")
    public ResponseEntity<ScriptEntity> deleteScript(@PathVariable Long scriptId) {

         scriptService.deleteScriptById(scriptId);
         return ResponseEntity.ok().build();
    }
}
