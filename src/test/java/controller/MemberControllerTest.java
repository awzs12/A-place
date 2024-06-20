package controller;

import example.controller.MemberController;
import example.entity.Member;
import example.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(MemberController.class)
class   MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberController(memberService)).build();
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/members/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("members/login"));
    }

    @Test
    void testCreateForm() throws Exception {
        mockMvc.perform(get("/members/login/createMemberForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("members/createMemberForm"))
                .andExpect(model().attributeExists("memberForm"));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/members/login/createMemberForm")
                        .param("name", "John Doe")
                        .param("password", "password123")
                        .param("email", "john.doe@example.com")
                        .param("phoneNumber", "123-456-7890"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/members/login"));

        verify(memberService).registerMember(any(Member.class));
    }
}
