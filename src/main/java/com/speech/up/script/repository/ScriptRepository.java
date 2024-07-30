package com.speech.up.script.repository;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<ScriptEntity, Long> {
    List<ScriptEntity> findByUser_UserId(Long userId);

    // ScriptEntity saveScript(ScriptEntity scriptEntity);

    // ScriptEntity updateScriptEntitiesByScriptId(ScriptEntity scriptEntity);
    //
    // ScriptEntity deleteScriptById(Long scriptId);
}
