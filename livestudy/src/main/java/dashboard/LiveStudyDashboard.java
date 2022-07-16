package dashboard;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class LiveStudyDashboard {

    private int issuesSize;
    private final Map<String, Double> resultMap = new HashMap<>();

    public static void main(String[] args) {
        LiveStudyDashboard dashboard = new LiveStudyDashboard();
        try {
            dashboard.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        // 깃허브 연결
        GitHub gitHub = new GitHubBuilder().
                withOAuthToken("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", "myUser").
                build();

        // GitHub에서 "리포지토리 이름"으로 호출하는 'owner/repo' 문자열에서 리포지토리 개체를 가져옵니다.
        GHRepository ghRepository = gitHub.getRepository("whiteship/live-study");
        // Gets issues. List<GHIssue> type
        List<GHIssue> issues = ghRepository.getIssues(GHIssueState.ALL);
        issuesSize = issues.size();
        Map<String, Integer> participantMap = new HashMap<>();

        for (int i = 0; i < issuesSize; i++) {
            GHIssue issue = issues.get(i);
            List<GHIssueComment> commentList = issue.getComments();
            Set<String> set = new HashSet<>();
            // 하나의 이슈에서 중복 제거
            addParticipantInSet(commentList, set);
            // 중복 제거한 유저 목록을 카운트해 Map에 담기
            countParticipantInMap(participantMap, set);
        }
        // 카운트 -> 비율 변경
        changeCountToRatioInMap(participantMap);
        // 출력
        printUserIdAndParticipationRate();

    }

    private void printUserIdAndParticipationRate() {
        Set<String> set = resultMap.keySet();
        System.out.println("Live Study Dash Board");
        System.out.println("======================================================");
        for (String userId : set) {
            System.out.println(userId + " : " + resultMap.get(userId) + "%");
        }
        System.out.println("======================================================");
    }

    private void changeCountToRatioInMap(Map<String, Integer> participantMap) {
        Set<String> keySet = participantMap.keySet();
        for (String userId : keySet) {
            int count = participantMap.get(userId);
            double ratio = getCountToRatio(issuesSize, count);
            resultMap.put(userId, ratio);
        }
    }

    private double getCountToRatio(int standard, int count) {
        // 소수점 두번째 자리까지 출력
        return Math.round((double) count / (double) standard * 100 * 100) / 100.0;
    }


    private void countParticipantInMap(Map<String, Integer> participantMap, Set<String> set) {
        for (String userId : set) {
            participantMap.put(userId, participantMap.getOrDefault(userId, 0) + 1);
        }
    }

    // 하나의 이슈에 동일한 사람이 여럿 있는경우 카운트하는 것을 방지
    private void addParticipantInSet(List<GHIssueComment> commentList, Set<String> set) throws IOException {
        for (GHIssueComment comment : commentList) {
            set.add(comment.getUser().getLogin());
        }
    }
}
