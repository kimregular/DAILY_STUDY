package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    /**
     * 이렇게 어노테이션을 사용해서 간편하게 Mock 객체를 생성할 수 있다.
     * 단, 이 방법을 사용하려면 테스트 클래스 위에 @ExtendWith(MockitoExtension.class)를 추가해야 테스트가 시작될 때 @Mock를 사용해서 Mock 객체를 생성한다는 것을 인지한다.
     *
     * MailSendClient 컴포넌트에 많은 메서드가 있고, MailService에서 이 컴포넌트를 사용해 메일을 보낼 때 MailSendClient의 sendEmail()을 비롯한 여러 메서드를 사용한다고 하자.
     * 나는 MailSendClient의 sendEmail()만 stubbing을 하고 싶고 나머지 메서드는 원본 객체의 기능이 동일하게 작동했으면 좋겠다. 이럴 때 @Spy를 사용하면 좋다.
     * @Spy는 실제 객체를 기반으로 만들어지기 때문에 그냥 when 절을 사용하면 안되고 doReturn()을 같은 문법을 사용해야 한다.
     */
    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    /**
     * 대상 클래스를 생성하고 의존하는 Mock 객체를 주입해주는 어노테이션
     * 이 클래스가 의존하는 다른 객체들은 @Mock 또는 @Spy로 선언된 모의 객체로 자동 주입받는다.
     */
    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        // 이렇게 어노테이션을 사용하지 않고 직접 Mock 객체를 생성할 수도 있다.
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

//        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(true);
        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);

//        doReturn(true)
//                .when(mailSendClient)
//                .sendEmail(anyString(), anyString(), anyString(), anyString());

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}
