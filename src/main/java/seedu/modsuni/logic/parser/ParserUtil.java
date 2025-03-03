package seedu.modsuni.logic.parser;

import static java.util.Objects.requireNonNull;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.modsuni.commons.core.index.Index;
import seedu.modsuni.commons.util.StringUtil;
import seedu.modsuni.logic.PrereqGenerator;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.Prereq;
import seedu.modsuni.model.person.Address;
import seedu.modsuni.model.person.Email;
import seedu.modsuni.model.person.Phone;
import seedu.modsuni.model.tag.Tag;
import seedu.modsuni.model.user.EmployDate;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.Salary;
import seedu.modsuni.model.user.student.EnrollmentDate;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final String MESSAGE_INVALID_SAVE_PATH = "Save path name should end with a .xml.";
    private static final String PATH_VALIDATION_REGEX = "^(\\w)+(\\.xml)";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String modsuni} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code modsuni} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String salary} into an {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_SALARY_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String employDate} into an {@code EmployDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code employDate} is invalid.
     */
    public static EmployDate parseEmployDate(String employDate) throws ParseException {
        requireNonNull(employDate);
        String trimmedEmployedDate = employDate.trim();
        if (!EmployDate.isValidEmployDate(trimmedEmployedDate)) {
            throw new ParseException(EmployDate.MESSAGE_DATE_CONSTRAINTS);
        }
        return new EmployDate(trimmedEmployedDate);
    }

    /**
     * Parses a {@code String username} into an {@code Username}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code username} is invalid.
     */
    public static Username parseUsername(String username) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!Username.isValidUsername(trimmedUsername)) {
            throw new ParseException(Username.MESSAGE_USERNAME_CONSTRAINTS);
        }
        return new Username(trimmedUsername);
    }

    /**
     * Parses a {@code String password} into an {@code Password}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code username} is invalid.
     */
    public static Password parsePassword(String password) throws ParseException {
        requireNonNull(password);
        String trimmedPassword = password.trim();
        if (!Password.isValidPassword(trimmedPassword)) {
            throw new ParseException(Password.MESSAGE_PASSWORD_CONSTRAINTS);
        }
        try {
            //TODO replace with EncryptionUtil
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(trimmedPassword.getBytes(StandardCharsets.UTF_8));
            return new Password(Password.toHexString(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new ParseException(String.format(Password.MESSAGE_PASSWORD_CONSTRAINTS));
        }
    }

    /**
     * Parses a {@code String enrollmentDate} into a {@code EnrollmentDate}.
     *
     * @throws ParseException if the given {@code enrollmentDate} is invalid.
     */
    public static EnrollmentDate parseEnrollmentDate(String enrollmentDate) throws ParseException {
        requireNonNull(enrollmentDate);
        String trimmedDate = enrollmentDate.trim();
        if (!EnrollmentDate.isValidEnrollmentDate(trimmedDate)) {
            throw new ParseException(EnrollmentDate.MESSAGE_DATE_CONSTRAINTS);
        }
        return new EnrollmentDate(trimmedDate);
    }
    /**
     * Parses a {@code String moduleCode} into a {@code Code}.
     */
    public static Code parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!Code.isValidCode(trimmedModuleCode)) {
            throw new ParseException(Code.MESSAGE_CODE_CONSTRAINTS);
        }
        return new Code(trimmedModuleCode);
    }

    /**
     * Parses a {@code String path} into a {@code Path}.
     */
    public static Path parsePath(String path) throws ParseException {
        requireNonNull(path);
        if (!path.matches(PATH_VALIDATION_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_SAVE_PATH);
        }
        return Paths.get(path.trim());

    }
    /**
     * Parses a {@code String prereq} into a {@code Prereq}.
     */
    public static Prereq parsePrereq(String prereq) throws ParseException {
        requireNonNull(prereq);
        String trimmedPrereq = prereq.trim();
        PrereqGenerator.checkValidPrereqString(trimmedPrereq);
        return new PrereqGenerator().generate(trimmedPrereq);
    }

    /**
     * Parses a {@code String prereq} into a {@code Prereq}.
     */
    public static String parseSwitchTab(String switchToTab) throws ParseException {
        requireNonNull(switchToTab);
        String trimmedSwitchToTab = switchToTab.trim();
        return trimmedSwitchToTab;
    }
}
