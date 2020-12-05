package Exam.Midterm2019;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
public class CountingCompletedSkills {
    /**
     *  You should implement this method.
     *  @param n the number of elements in skills.
     *  @param skills the sorted array of `Skill`s (see Library for their implementation) to look through. Note that you should use entries skills[1] to skills[n]!
     *  @return the number of completed skills in the sorted array.
     */
    public static int numberOfCompletedSkills(int n, Skill[] skills) {
        // TODO
        if(n == 0) return 0;
        if(n == 1)
        {
            if(skills[1].isCompleted())
            {
                return 1;
            }
            return 0;
        }
        return solver(skills,1,n,n);
    }

    public static int solver(Skill[] skills, int start, int end, int n)
    {
        if(start == end && !skills[start].isCompleted()) return n - start;
        if(start == end && skills[start].isCompleted()) return n - start + 1;
        int middle = (start + end) / 2;
        if(middle == 1 && skills[middle].isCompleted()) return n;

        Skill underSkill = skills[middle-1];
        Skill middleSkill = skills[middle];
        Skill aboveSkill = skills[middle+1];

        if(underSkill.isCompleted() != aboveSkill.isCompleted())
        {
            if(middleSkill.isCompleted()) return n - middle + 1;
            else return n - middle;
        }
        else
        {
            if(middleSkill.isCompleted()) return solver(skills,start,middle - 1,n);
            else return solver(skills,middle + 1,end,n);
        }
    }

    public static void main(String[] args) {
        int solution = 2;
        int n = 3;
        Skill[] skills = new Skill[4];
        skills[1] = new Skill("Binary counting", false);
        skills[2] = new Skill("Huffman encoding", true);
        skills[3] = new Skill("Exchange arguments", true);
        System.out.println(numberOfCompletedSkills(n, skills));
    }
}
class Skill {

    private String name;

    private boolean completed;

    public Skill(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

