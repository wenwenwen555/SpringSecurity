//package com.kun;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.write.builder.ExcelWriterBuilder;
//import com.kun.entry.Student;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootApplication
//public class StudentWriteDemo {
//    public static void main(String[] args) {
//
//        List<Student> students = initData();
//        /*
//            String pathName 写入文件的路径
//            Class head      写入文件的对象类型
//            默认写入到07的xlsx中，如果想要写入xls，可以指定类型（待验证）
//         */
//        ExcelWriterBuilder workBook = EasyExcel.write("E:\\Project\\SpringSecurity\\src\\main\\java\\AllExcel\\lds学员表.xlsx", Student.class);
//
//        // sheet方法参数： 工作表的顺序号（从0开始）或者工作表的名字
//        workBook.sheet().doWrite(students);
//    }
//    @Test
//    private static List<Student> initData() {
//        ArrayList<Student> students = new ArrayList<Student>();
//        for (int i = 0; i < 10; i++) {
//         Student data = new Student();
//            data.setName("lds学号0" + i);
//            data.setBirthday(new Date());
//            data.setGender("男");
//            students.add(data);
//        }
//        return students;
//    }
//}
