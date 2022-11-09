package chapter14.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ex14_9 extends JFrame {
    private JLabel jl;
    private JButton jb1, jb2;

    public Ex14_9() {
        Container ct = getContentPane();
        // 전체 레이아웃은 FlowLayout
        ct.setLayout(new FlowLayout());

        // 버튼 생성
        jb1 = new JButton("사랑합니다.");
        jb2 = new JButton("행복합니다.");
        jl = new JLabel("버튼을 누르세요.");

        // 리스너를 구현하는 클래스를 액션리스너로 등록함
        jb1.addActionListener(new EventProcess());
        jb2.addActionListener(new EventProcess());

        // 컨테이너에 버튼 및 레이블 추가
        ct.add(jb1);
        ct.add(jb2);
        ct.add(jl);

        // 옵션 설정
        setTitle("Event Test2");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class EventProcess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jl.setText(e.getActionCommand());
        }
    }
}

class Ex14_9_Main {
    public static void main(String[] args) {
        new Ex14_9();
    }
}
