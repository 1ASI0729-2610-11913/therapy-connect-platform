package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.LiveSessionQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionMode;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.LiveSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiveSessionQueryServiceImpl implements LiveSessionQueryService {

    private final LiveSessionRepository liveSessionRepository;

    public LiveSessionQueryServiceImpl(LiveSessionRepository liveSessionRepository) {
        this.liveSessionRepository = liveSessionRepository;
    }

    @Override
    public Optional<LiveSession> handle(GetLiveSessionByIdQuery query) {
        return liveSessionRepository.findById(query.liveSessionId());
    }

    @Override
    public List<LiveSession> handle(GetAllLiveSessionsQuery query) {
        return liveSessionRepository.findAll();
    }

    @Override
    public List<LiveSession> handle(GetLiveSessionsByTeacherIdQuery query) {
        return liveSessionRepository.findByTeacherId(query.teacherId());
    }

    @Override
    public List<LiveSession> handle(GetLiveSessionsByStudentIdQuery query) {
        return liveSessionRepository.findByStudentId(query.studentId());
    }

    @Override
    public List<LiveSession> handle(GetLiveSessionsByModeQuery query) {
        var mode = SessionMode.valueOf(query.sessionMode().toUpperCase());
        return liveSessionRepository.findBySessionMode(mode);
    }
}
