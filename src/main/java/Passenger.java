public class Passenger {

    private int passengerId; // מספר מזהה
    private boolean survived; // אם שרד
    private int pClass;
    private String name;
    private String sex;
    private double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private char embarked;



    public Passenger (String text) {
        String[] dataItem = text.split(",");

        this.passengerId = Integer.parseInt(dataItem[0]);
        this.survived = (Integer.parseInt(dataItem[1]) == 1);
        this.pClass = Integer.parseInt(dataItem[2]);
        this.name = dataItem[3] + "," + dataItem[4];
        this.sex = dataItem[5];
        if (dataItem[6].equals("")) {
            this.age = -1;
        }else {
            this.age = Double.parseDouble(dataItem[6]);
        }
        this.sibSp = Integer.parseInt(dataItem[7]);
        this.parch = Integer.parseInt(dataItem[8]);
        this.ticket = dataItem[9];
        this.fare = Double.parseDouble(dataItem[10]);
        this.cabin = dataItem[11];
        if (dataItem.length == 13) {
            this.embarked = dataItem[12].charAt(0);
        }
    }

    public String getFormattedName() {
        String fullName = "";
        String[] split1 = this.name.split(",");
        String[] split2 = split1[1].split("\\.");
        fullName = split2[1].substring(1,split2[1].length() - 1) +  " " + split1[0].substring(1);
        return fullName;
    }


    public String toString() {
        return
                + passengerId +
                "," + (survived ? "1" : "0") +
                "," + pClass +
                "," + getFormattedName()+
                "," + sex +
                "," + ((age == -1) ? "":age)  +
                "," + sibSp +
                "," + parch +
                "," + ticket +
                "," + fare +
                "," + cabin+
                "," + embarked + "\n";
    }

    public boolean isIdInRange (int min,int max) {
        boolean isInRange = false;
        if (this.passengerId >= min && this.passengerId <= max) {
            isInRange = true;
        }
        return isInRange;
    }

    public boolean isNameCorrect (String name) {
        boolean isCorrect = false;
        if (getFormattedName().equals(name) || name.equals("")) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public boolean isSameClass (int pClass) {
        boolean isSame = false;
        if (pClass == this.pClass || pClass == 0) {
            isSame = true;
        }
        return isSame;
    }

    public boolean isSameSex (String sex) {
        boolean isSame = false;
        if (sex.equals(this.sex) || sex.equals("All")) {
            isSame = true;
        }
        return isSame;
    }

}
