package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;

public class DocumentUserConstant {

    public static String name = "홍길동";
    public static String number = "1234";

    public static User user = User.builder()
            .name(name)
            .build();

    public static Student student = Student.builder()
            .number(number)
            .user(user)
            .build();

    public static Document document = Document.builder()
            .student(student)
            .build();

}
