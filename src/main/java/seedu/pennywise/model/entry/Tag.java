package seedu.pennywise.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.AppUtil.checkArgument;

import java.util.List;

/**
 * Represents a Tag in the PennyWise application.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(EntryType, String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric";
    public static final String INCOME_CONSTRAINTS = "Income tag must only be one of the following: \n"
            + "Salary, Allowance, Profit, Investment, Gifts, Others";
    public static final String EXPENDITURE_CONSTRAINTS = "Expenditure tag must only be one of the following: \n"
            + "Food, Groceries, Entertainment, Transport, Education, Housing, Others";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final List<String> INCOME_TAGS = List.of(
            "Salary",
            "Allowance",
            "Profit",
            "Investment",
            "Gifts",
            "Others");
    public static final List<String> EXPENDITURE_TAGS = List.of(
            "Food",
            "Groceries",
            "Entertainment",
            "Transport",
            "Education",
            "Housing",
            "Others");
    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(EntryType type, String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(type, tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     *
     * @return True if a given string is a valid tag name.
     */
    public static boolean isValidTagName(EntryType type, String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        switch (type.getEntryType()) {
        case INCOME:
            if (!INCOME_TAGS.contains(test)) {
                return false;
            }
            break;
        case EXPENDITURE:
            if (!EXPENDITURE_TAGS.contains(test)) {
                return false;
            }
            break;
        default:
            return false;
        }
        return true;

    }

    /**
     * Returns the name of the tag.
     *
     * @return Name of the tag.
     */
    public String getTagName() {
        return tagName;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag // instanceof handles nulls
            && getTagName().equals(((Tag) other).getTagName())); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Formats the tag as text for viewing.
     *
     * @return Formatted tag.
     */
    public String toString() {
        return getTagName();
    }

}
