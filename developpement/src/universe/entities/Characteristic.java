package universe.entities;

import java.util.Random;

public class Characteristic {

    public Gender gender;
    public int smartness;
    public int actionMaxLength = 1;
    public CharacterState state;

    public Characteristic(Integer smartness) {
	super();
	this.gender = this.getGender();
	this.smartness = smartness;
	this.state = CharacterState.Ok;
    }

    public Characteristic(Gender gender, Integer smartness,
	    Integer actionMaxLength, CharacterState state) {
	super();
	this.gender = gender;
	this.smartness = smartness;
	this.actionMaxLength = actionMaxLength;
	this.state = state;
    }

    /**
     * @param cara
     *            A strictly positive int
     * @return The D&D modifier for this characteristic (-5 / infinite)
     */
    public int getModifier(int cara) {
	return cara / 2;// (cara - 10) / 2;
    }

    /**
     * @return A random Gender for the character
     */
    private Gender getGender() {
	int random = new Random().nextInt(100);
	if (random < 49)
	    return Gender.Female;
	else if (random < 98)
	    return Gender.Male;
	else if (random < 99)
	    return Gender.Hermaphrodite;
	else
	    return Gender.Transgender;
    }

}
