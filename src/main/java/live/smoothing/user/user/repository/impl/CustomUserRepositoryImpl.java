package live.smoothing.user.user.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.user.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory factory;

    @Override
    public List<WaitingUser> findWaitingUsers(int page, int size) {

        List<WaitingUser> waitingUserList = factory
                .select(Projections.constructor(WaitingUser.class,
                        user.userId,
                        user.userName,
                        user.lastAccess))
                .from(user)
                .where(user.userState.eq(UserState.NOT_APPROVED))
                .offset((long) page * size)
                .limit(size)
                .orderBy(user.lastAccess.asc())
                .fetch();

        return waitingUserList;
    }

    @Override
    public List<WaitingUser> findWaitingUsers() {

        return factory
                .select(Projections.constructor(WaitingUser.class,
                        user.userId,
                        user.userName,
                        user.lastAccess))
                .from(user)
                .where(user.userState.eq(UserState.NOT_APPROVED))
                .orderBy(user.lastAccess.asc())
                .fetch();
    }
}