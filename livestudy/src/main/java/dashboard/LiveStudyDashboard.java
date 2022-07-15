package dashboard;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

public class LiveStudyDashboard {
    public static void main(String[] args) throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken("personal_token", "myUser").build();

        // GitHub에서 "리포지토리 이름"으로 호출하는 'owner/repo' 문자열에서 리포지토리 개체를 가져옵니다.
        GHRepository ghRepository = github.getRepository("whiteship/live-study");
        // Gets issues. List<GHIssue> type
        List<GHIssue> issues = ghRepository.getIssues(GHIssueState.ALL);

        // 아직 테스트를 위해 메소드만 꺼내놓음
        for (GHIssue issue : issues) {
            List<GHIssueComment> comments = issue.getComments();
            for (GHIssueComment comment : comments) {
                GHUser user = comment.getUser();
                String login = user.getLogin();
                System.out.println(login);
            }
        }
        System.out.println("pass");
    }
}
