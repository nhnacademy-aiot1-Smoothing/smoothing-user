//package live.smoothing.user.role.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import live.smoothing.user.role.dto.response.RoleResponse;
//import live.smoothing.user.role.dto.request.CreateRoleRequest;
//import live.smoothing.user.role.dto.request.UpdateRoleRequest;
//import live.smoothing.user.role.service.RoleService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(RoleController.class)
//class RoleControllerTest {
//
//    Long ROLE_ID = 1L;
//    String ROLE_USER = "ROLE_USER";
//    String ROLE_ADMIN = "ROLE_ADMIN";
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    RoleService roleService;
//
//    @BeforeEach
//    void setup() {
//
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("권한명 조회")
//    void getRole() throws Exception {
//
//        RoleResponse roleResponse = new RoleResponse(ROLE_ID, ROLE_USER);
//
//        Mockito.when(roleService.getRole(ROLE_ID)).thenReturn(roleResponse);
//
//        mockMvc.perform(get("/api/role/{roleId}", ROLE_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
//                .andExpect(jsonPath("$.roleId").value(ROLE_ID))
//                .andExpect(jsonPath("$.roleInfo").value(ROLE_USER));
//    }
//
//    @Test
//    @DisplayName("권한 목록 조회")
//    void getAllRoles() throws Exception {
//
//        List<RoleResponse> roleResponseList = Arrays.asList(
//                new RoleResponse(ROLE_ID, ROLE_USER),
//                new RoleResponse(ROLE_ID + 1, ROLE_ADMIN)
//        );
//
//        Mockito.when(roleService.getAllRoles()).thenReturn(roleResponseList);
//
//        mockMvc.perform(get("/api/role/list"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].roleId").value(ROLE_ID))
//                .andExpect(jsonPath("$[0].roleInfo").value(ROLE_USER))
//                .andExpect(jsonPath("$[1].roleId").value(ROLE_ID + 1))
//                .andExpect(jsonPath("$[1].roleInfo").value(ROLE_ADMIN));
//    }
//
//    @Test
//    @DisplayName("권한 정보 생성")
//    void createRole() throws Exception {
//
//        CreateRoleRequest request = new CreateRoleRequest(ROLE_USER);
//
//        String requestJson = objectMapper.writeValueAsString(request);
//
//        doNothing().when(roleService).createRole(request);
//
//        mockMvc.perform(post("/api/role")
//                        .contentType(APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.message").value("권한 정보 생성 완료"));
//    }
//
//    @Test
//    @DisplayName("권한 정보 업데이트")
//    void updateRole() throws Exception {
//
//        UpdateRoleRequest request = new UpdateRoleRequest(ROLE_ADMIN);
//
//        String requestJson = objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(put("/api/role/{roleId}", ROLE_ID)
//                        .contentType(APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("권한명 수정 완료"));
//    }
//
//    @Test
//    @DisplayName("권한 정보 삭제")
//    void deleteRole() throws Exception {
//
//        doNothing().when(roleService).deleteRole(ROLE_ID);
//
//        mockMvc.perform(delete("/api/role/{roleId}", ROLE_ID))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("권한 정보 삭제 완료"));
//    }
//}