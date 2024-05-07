package live.smoothing.user.user.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.dto.response.UserInfoResponse;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.user.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory factory;

    @Override
    public List<WaitingUser> findWaitingUsers(Pageable pageable) {

        return factory
                .select(Projections.constructor(WaitingUser.class,
                        user.userId,
                        user.userName,
                        user.lastAccess))
                .from(user)
                .where(user.userState.eq(UserState.NOT_APPROVED))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.lastAccess.asc())
                .fetch();
    }

    @Override
    public List<UserInfoResponse> findAllUsers(Pageable pageable) {

        return factory
                .select(Projections.constructor(UserInfoResponse.class,
                        user.userId,
                        user.userName,
                        user.userEmail))
                .from(user)
                .where(user.userState.eq(UserState.APPROVED))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}