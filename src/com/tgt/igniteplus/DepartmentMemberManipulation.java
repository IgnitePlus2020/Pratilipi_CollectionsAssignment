package com.tgt.igniteplus;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class DepartmentMemberManipulation {

    static HashMap<String, List<IgniteMembers>> DepartmentMembersMap = new HashMap<>();

    static ArrayList<String> DepartmentList = new ArrayList<>();

    static List<IgniteMembers> memberObj = new CopyOnWriteArrayList<>();

    Scanner in = new Scanner(System.in);

//----------------------------------------------------------------------------------------------------------------------
    //Method to list all the departments

    protected void displayDepartmentList() {

        //If there are no departments in the list
        if (DepartmentList.size() == 0)
            System.out.println("No departments to display!");

        else {
            int i = 1;
            System.out.println("\nDEPARTMENTS:");
            for (String department : DepartmentList) {
                System.out.println(i + ".\t" + department);
                i++;
            }
        }
    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to create a new department

    protected void createDepartment() {

        System.out.println("\nEnter name of the new department:\t");
        String newDept = in.nextLine();

        //Add to department list
        DepartmentList.add(newDept);

        //Add to Map
        DepartmentMembersMap.put(newDept, null);

        //Every department must have at least one member
        System.out.println("Now add a new member to " + newDept);
        createMember(newDept);

    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to create a new member and add him/her to an existing department

    public void createMember(String deptParam) {

        Set<String> SkillSet = new HashSet<>();

        String department = deptParam;

        String newName, newCollege, newSkill;
        int newAge, deptChoice;

        System.out.print("\nEnter member's name:\t");
        newName = in.next();

        //Check if member name already exists (Name as Unique Element)
        do {
            for (IgniteMembers im : memberObj) {
                if (im.getName().contains(newName)) {
                    System.out.print("\nName already exists!\n" +
                            "Enter a UNIQUE name:\t");
                    newName = in.next();
                }
            }
        } while (memberObj.contains(newName));

        System.out.print("Enter member's age:\t");
        newAge = in.nextInt();

        System.out.print("Enter member's college:\t");
        newCollege = in.next();

        //Accept Skills until "D" or "d" is pressed
        System.out.print("Enter member's Skill Set\t" + "(Press D/d when done):\n");
        while (true) {
            newSkill = in.nextLine();
            if (newSkill.equalsIgnoreCase("D")) {
                break;
            }
            SkillSet.add(newSkill);
        }

        //If new member was created, add to an existing department
        if (department == null) {
            System.out.println("\nAdd '" + newName + "' to one of the departments:");
            displayDepartmentList();

            //If no departments are available, Create one first
            if (DepartmentList.size() == 0) {
                System.out.println("Add one first!");
                return;
            }

            //Otherwise accept the choice
            System.out.print("\nEnter your choice:\t");
            deptChoice = in.nextInt();

            int k = 1;
            for (String deptObj : DepartmentList) {
                if (k == deptChoice) {
                    department = deptObj;
                    break;
                }
                k++;
            }
        }

        //Add new member to the IgnteMembers list
        IgniteMembers member = new IgniteMembers(newName, newCollege, SkillSet, newAge);
        memberObj.add(member);

        //If new department was created, add the new member to this department
        if (DepartmentMembersMap.get(department) == null) {
            List<IgniteMembers> deptMember = new ArrayList<>();
            deptMember.add(member);
            DepartmentMembersMap.put(department, deptMember);
        } else
            DepartmentMembersMap.get(department).add(member);
    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to remove a department

    protected void removeDepartment() {

        int deptChoice;
        String deleteDept = null;

        displayDepartmentList();
        System.out.println("\nEnter your choice:\t");
        deptChoice = in.nextInt();
        int k = 1;

        //Extracting name of department from choice
        for (String deptObj : DepartmentList) {
            if (k == deptChoice) {
                deleteDept = deptObj;
                break;
            }
            k++;
        }

        DepartmentMembersMap.remove(deleteDept);
        DepartmentList.remove(deleteDept);

        /*String swapChoice = "N";
        if(DepartmentMembersMap.get(deleteDept) != null) {
            System.out.print(deleteDept + " department contains members.\n" +
                    "Would you like to swap their departments before deleting " + deleteDept + "\t(Y/N):\t");
            swapChoice = in.next();
        }

        if(swapChoice.equalsIgnoreCase("N")) {
            DepartmentMembersMap.remove(deleteDept);
            DepartmentList.remove(deleteDept);
            //System.out.println("Successfully Removed!\n");
        }

        else {
            swapDept();
        }*/

    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to list members the according to department

    protected void displayMemberDeptWise() {

        System.out.println("\nDEPARTMENT\t\t\t|\t\tMEMBER NAME\n" +
                "-------------------------------------------------");
        for (String department : DepartmentMembersMap.keySet()) {
            for (IgniteMembers member : DepartmentMembersMap.get(department)) {
                System.out.println(department + "\t\t|\t\t" + member.getName());
            }
        }
    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to List members according to given skill

    protected void displayMemberSkillWise() {

        System.out.print("Enter the skill:\t");
        String skill = in.nextLine();
        System.out.println("\nList of members having " + skill + " as a skill:\n" +
                "---------------------------------------------------------");
        System.out.println("NAME\t\t|\t\tSKILLSET");
        int flag = 0;
        for (IgniteMembers im : memberObj) {
            if (im.getSkillSet().contains(skill)) {
                System.out.println(im.getName() + "\t\t|\t\t" + im.getSkillSet());
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.println("\nNone of the members possess " + skill + " as a skill!");
    }


//----------------------------------------------------------------------------------------------------------------------
    //Method to change department of a member

    protected boolean swapDept() {

        int deptExists = 0;
        int memberExists = 0;
        String department = null;

        System.out.print("Enter member's name who's department you want to change:\t");
        String memName = in.next();


        //Enter a valid member
        do {
            for (IgniteMembers member : memberObj) {
                if (member.getName().contains(memName)) {
                    memberExists = 1;
                } else {
                    System.out.print("Member doesn't exist!\n" +
                            "Enter an existing member's name:\t");
                    memName = in.next();
                    memberExists = 0;
                }


            }
        } while (memberExists == 0);


        //Enter a valid department option
        do {
            displayDepartmentList();
            System.out.println("Enter your choice of department:");
            int deptChoice = in.nextInt();
            int k = 1;
            for (String deptObj : DepartmentList) {
                if (k == deptChoice) {
                    department = deptObj;
                    deptExists = 1;
                    break;
                }
                k++;
            }

            if (deptExists == 0)
                System.out.print("Department doesn't exist!\n" +
                        "Enter an existing department:\t");
        } while (deptExists == 0);


        //Check if swap is possible or not
        for (String deptIterator : DepartmentMembersMap.keySet()) {
            List<IgniteMembers> members = DepartmentMembersMap.get(deptIterator);

            for (IgniteMembers member : members) {
                if (member.getName().equals(memName)) {

                    //Since every department must have atleast one member
                    if (members.size() == 1) {
                        System.out.println(memName + " is the only member of the department " + deptIterator);
                        System.out.println("Swap is not possible!");
                        return false;
                    } else {

                        DepartmentMembersMap.get(department).add(member);
                        DepartmentMembersMap.get(deptIterator).remove(member);
                    }
                }
            }
        }
        return true;
    }


//----------------------------------------------------------------------------------------------------------------------
    //Add a new skill to the skill set of every member of a department

    protected void addNewSkill() {

        System.out.print("Enter the new skill:\t");
        String newSkill = in.next();

        System.out.print("Choose a department\n");
        displayDepartmentList();
        System.out.print("Enter your choice:\t");
        int deptChoice = in.nextInt();

        String department = null;
        int k = 1;
        for (String deptObj : DepartmentList) {
            if (k == deptChoice) {
                department = deptObj;
                break;
            }
            k++;
        }

        for (IgniteMembers member : DepartmentMembersMap.get(department)) {
            Set<String> skill = member.getSkillSet();
            skill.add(newSkill);
            member.setSkillSet(skill);
        }

        System.out.println();
        for (IgniteMembers member : DepartmentMembersMap.get(department)) {
            System.out.println("Name:\t" + member.getName() + "\t\t\tSkills:\t" + member.getSkillSet());
        }
    }
}
