package live.smoothing.user.user.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.user.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<WaitingUser> findWaitingUsers(int page, int size) {

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

        long totalCount = factory
                .selectFrom(user)
                .where(user.userState.eq(UserState.NOT_APPROVED))
                .fetchCount();

        return new PageImpl<>(waitingUserList, PageRequest.of(page, size), totalCount);
    }
}