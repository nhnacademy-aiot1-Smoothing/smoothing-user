package live.smoothing.user.hook.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import live.smoothing.user.hook.dto.response.HookUrlResponse;
import live.smoothing.user.hook.repository.CustomHookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.user.hook.entity.QHook.hook;

@Repository
@RequiredArgsConstructor
public class CustomHookRepositoryImpl implements CustomHookRepository {

    private final JPAQueryFactory factory;
    @Override
    public List<HookUrlResponse> getUsersHookList(Integer hookTypeId) {

        return factory
                .select(Projections.constructor(HookUrlResponse.class,
                        hook.user.userId, hook.hookUrl))
                .from(hook)
                .where(hook.hookType.hookTypeId.eq(hookTypeId))
                .fetch();
    }
}
