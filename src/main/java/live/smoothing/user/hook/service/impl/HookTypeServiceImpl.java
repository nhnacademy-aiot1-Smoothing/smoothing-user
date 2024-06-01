package live.smoothing.user.hook.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.hook.dto.request.HookTypeCreateRequest;
import live.smoothing.user.hook.dto.request.HookTypeModifyRequest;
import live.smoothing.user.hook.entity.HookType;
import live.smoothing.user.hook.repository.HookTypeRepository;
import live.smoothing.user.hook.service.HookTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HookTypeServiceImpl implements HookTypeService {

    private final HookTypeRepository hookTypeRepository;

    @Override
    public HookType getHookType(Integer hookTypeId) {

        return hookTypeRepository.findById(hookTypeId).orElseThrow(() -> new ServiceException(ErrorCode.HOOKTYPE_NOT_FOUND));
    }

    @Override
    public List<HookType> getHookTypes() {

        return hookTypeRepository.findAll();
    }

    @Override
    public void createHookType(HookTypeCreateRequest request) {

        if (hookTypeRepository.existsHookTypeByHookTypeName(request.getHookTypeName())) {
            throw new ServiceException(ErrorCode.DUPLICATED_HOOKTYPE);
        }

        HookType hookType = new HookType(request.getHookTypeName());

        hookTypeRepository.save(hookType);
    }

    @Override
    public void modifyHookType(Integer hookTypeId, HookTypeModifyRequest request) {

        HookType hookType = hookTypeRepository.findById(hookTypeId).orElseThrow(() -> new ServiceException(ErrorCode.HOOKTYPE_NOT_FOUND));

        hookType.modifyHookTypeName(request.getHookTypeName());

        hookTypeRepository.save(hookType);
    }

    @Override
    public void deleteHookType(Integer hookTypeId) {

        HookType hookType = hookTypeRepository.findById(hookTypeId).orElseThrow(() -> new ServiceException(ErrorCode.HOOKTYPE_NOT_FOUND));

        hookTypeRepository.delete(hookType);
    }
}
