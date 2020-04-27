package com.tgt.igniteplus;

import java.util.Set;

public class IgniteMembers {
    private String Name;
    private String College;
    private Set<String> SkillSet;
    private int Age;

    public IgniteMembers(String name, String college, Set<String> skillSet, int age) {
        Name = name;
        College = college;
        SkillSet = skillSet;
        Age = age;
    }

    public IgniteMembers() {
    }


    @Override
    public String toString() {
        return "Ignite Members:\n" +
                "Name: '" + Name + '\'' +
                "\tCollege: '" + College + '\'' +
                "\tSkillSet: " + SkillSet +
                "\tAge: " + Age;
    }

    public String getName() {
        return Name;
    }

    public String getCollege() {
        return College;
    }

    public Set<String> getSkillSet() {
        return SkillSet;
    }

    public int getAge() {
        return Age;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCollege(String college) {
        College = college;
    }

    public void setSkillSet(Set<String> skillSet) {
        SkillSet = skillSet;
    }

    public void setAge(int age) {
        Age = age;
    }
}
