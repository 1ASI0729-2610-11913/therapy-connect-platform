package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LiveSessionQueryService {
    Optional<LiveSession> handle(GetLiveSessionByIdQuery query);
    List<LiveSession> handle(GetAllLiveSessionsQuery query);
    List<LiveSession> handle(GetLiveSessionsByTeacherIdQuery query);
    List<LiveSession> handle(GetLiveSessionsByStudentIdQuery query);
    List<LiveSession> handle(GetLiveSessionsByModeQuery query);
}
