/*
1) User Should be able to Create, Delete and Update the Department.
2) Each and every department should have at least 1 Ignite Member
3) Department can have more than 1 Ignite Member
Features:
1) Swapping of Ignite Members from one dept to another dept
2) Add a new Skillset to all the Ignite members for the specific dept
3) List all the users who have the Skill of “Java” with department name
4) Make Sure unique Ignite Members are added into the department (Assuming the
unique attribute is Name)
5) Validations at all operation is mandatory
 */
package com.tgt.igniteplus;

import java.util.Scanner;

public class Main {
    static DepartmentMemberManipulation manipulationObj = new DepartmentMemberManipulation();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*-------------------------------------------------------------------------------*\n" +
                    "Option Menu:\n" +
                    "1. Display list of Departments\n" +
                    "2. Add a new Department\n" +
                    "3. Delete a Department\n" +
                    "4. Display all Members based on departments\n" +
                    "5. Create a Member and add him/her to a department\n" +
                    "6. Display all members possessing a given skill\n" +
                    "7. Swap department of a member\n" +
                    "8. Add a new skill to the skill set of all members of a department\n" +
                    "*-------------------------------------------------------------------------------*");
            System.out.print("\nEnter your option:\t");
            int option = in.nextInt();

            switch (option) {
                case 1:
                    manipulationObj.displayDepartmentList();
                    break;

                case 2:
                    manipulationObj.createDepartment();
                    break;

                case 3:
                    manipulationObj.removeDepartment();
                    break;

                case 4:
                    manipulationObj.displayMemberDeptWise();
                    break;

                case 5:
                    manipulationObj.createMember(null);
                    break;

                case 6:
                    manipulationObj.displayMemberSkillWise();
                    break;

                case 7:
                    boolean flag = manipulationObj.swapDept();
                    if (flag == true)
                        System.out.println("Swap Successful!");
                    break;

                case 8:
                    manipulationObj.addNewSkill();
                    break;

                default:
                    System.out.print("Invalid Option!\n" +
                            "Enter a valid option.\n");
            }

            System.out.print("*-------------------------------------------------------------------------------*\n" +
                    "Do you want to continue? (0/1):\t");
            choice = in.nextInt();
        } while (choice == 1);

        System.exit(0);
    }
}