package gymmanagement;

import org.junit.Test;

import static org.junit.Assert.*;

public class PremiumTest {

    @Test
    public void membershipFee() {
        //just one test case to see if == to value
        double testValuePremium = 659.89;
        double testValueFamily = 209.96;
        double testValueStandard = 149.96;

        //should be equal to 149.96 as a standard member
        Member Niklas = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        double membershipFeeValue = Niklas.membershipFee();
        assertTrue(membershipFeeValue == testValueStandard);

        //should be equal to 209.96 as a Family member
        Family Nik = new Family("Niklas", "Bloom", new Date("6/2/2000"), new Date("6/2/2024"), Location.Piscataway);
        double familyMembershipFeeValue = Nik.membershipFee();
        assertTrue(familyMembershipFeeValue == testValueFamily);

        //should be equal to 659.89 as a Premium Member
        Premium Max = new Premium("Max", "Yucim", new Date("6/2/2000"), new Date("6/2/2024"), Location.Piscataway);
        double premiumMembershipFeeValue = Max.membershipFee();
        assertTrue(premiumMembershipFeeValue == testValuePremium);

    }
}