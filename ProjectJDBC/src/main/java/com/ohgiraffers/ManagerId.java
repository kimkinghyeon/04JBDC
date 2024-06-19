package com.ohgiraffers;

import com.ohgiraffers.all.AllEmployee;
import com.ohgiraffers.delete.DeleteEmployee;
import com.ohgiraffers.insert.InsertEmployee;
import com.ohgiraffers.update.UpdateEmployee;

import java.util.Scanner;

public class ManagerId {

    public void test() {
        UpdateEmployee updateEmployee = new UpdateEmployee();
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        AllEmployee allEmployee = new AllEmployee();
        InsertEmployee insertEmployee = new InsertEmployee();

        while (true) {

            System.out.println("==== ==== ==== ==== ==== ====");
            System.out.println("사원 정보 프로그램 \n" +
                    "1. 사원등록, 2. 사원업데이트, 3. 사원삭제 , 4.사원 전체조회 , " +
                    "5. 종료");
            System.out.print("입력 : ");
            Scanner sc = new Scanner(System.in);
            int cho = sc.nextInt();
            switch (cho){
                case 1:
                    insertEmployee.insertEmp();
                    break;
                case 2:
                    updateEmployee.updateEmp();
                    break;
                case 3:
                    deleteEmployee.deleteEmp();
                    break;
                case 4:
                    allEmployee.allEmp();
                    break;
                case 5:
                    System.out.println("==== ==== ==== ==== ==== ====\n" +
                            "프로그램을 종료합니다.");
                    return;

            }

        }
    }
}
