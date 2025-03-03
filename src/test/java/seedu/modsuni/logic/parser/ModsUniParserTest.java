package seedu.modsuni.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.modsuni.logic.commands.CommandTestUtil.CODE_DESC_ACC1002;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_PATH;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.modsuni.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.modsuni.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.modsuni.testutil.TypicalSavePaths.PATH_USERDATA_MAX;
import static seedu.modsuni.testutil.TypicalSavePaths.PATH_USERDATA_SEB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.modsuni.logic.commands.AddAdminCommand;
import seedu.modsuni.logic.commands.AddCommand;
import seedu.modsuni.logic.commands.AddModuleToDatabaseCommand;
import seedu.modsuni.logic.commands.AddModuleToStudentStagedCommand;
import seedu.modsuni.logic.commands.AddModuleToStudentTakenCommand;
import seedu.modsuni.logic.commands.ClearCommand;
import seedu.modsuni.logic.commands.DeleteCommand;
import seedu.modsuni.logic.commands.EditStudentCommand;
import seedu.modsuni.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.modsuni.logic.commands.ExitCommand;
import seedu.modsuni.logic.commands.FindCommand;
import seedu.modsuni.logic.commands.GenerateCommand;
import seedu.modsuni.logic.commands.HelpCommand;
import seedu.modsuni.logic.commands.HistoryCommand;
import seedu.modsuni.logic.commands.ListCommand;
import seedu.modsuni.logic.commands.LoginCommand;
import seedu.modsuni.logic.commands.LogoutCommand;
import seedu.modsuni.logic.commands.RedoCommand;
import seedu.modsuni.logic.commands.RegisterCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromDatabaseCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromStudentStagedCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromStudentTakenCommand;
import seedu.modsuni.logic.commands.RemoveUserCommand;
import seedu.modsuni.logic.commands.SearchCommand;
import seedu.modsuni.logic.commands.SelectCommand;
import seedu.modsuni.logic.commands.ShowModuleCommand;
import seedu.modsuni.logic.commands.UndoCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.CodeStartsKeywordsPredicate;
import seedu.modsuni.model.module.Module;
import seedu.modsuni.model.person.NameContainsKeywordsPredicate;
import seedu.modsuni.model.person.Person;
import seedu.modsuni.model.user.Admin;
import seedu.modsuni.model.user.student.Student;
import seedu.modsuni.testutil.AdminBuilder;
import seedu.modsuni.testutil.AdminUtil;
import seedu.modsuni.testutil.CredentialBuilder;
import seedu.modsuni.testutil.CredentialUtil;
import seedu.modsuni.testutil.EditStudentDescriptorBuilder;
import seedu.modsuni.testutil.ModuleBuilder;
import seedu.modsuni.testutil.ModuleUtil;
import seedu.modsuni.testutil.PersonBuilder;
import seedu.modsuni.testutil.PersonUtil;
import seedu.modsuni.testutil.StudentBuilder;
import seedu.modsuni.testutil.StudentUtil;

public class ModsUniParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ModsUniParser parser = new ModsUniParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addModuleS() throws Exception {
        AddModuleToStudentStagedCommand command = (AddModuleToStudentStagedCommand) parser
                .parseCommand(AddModuleToStudentStagedCommand.COMMAND_WORD + CODE_DESC_ACC1002);
        assertEquals(new AddModuleToStudentStagedCommand(new ArrayList<>(Arrays.asList(new Code("ACC1002"))),
                new HashSet<>()), command);
    }

    @Test
    public void parseCommand_addModuleT() throws Exception {
        AddModuleToStudentTakenCommand command = (AddModuleToStudentTakenCommand) parser
                .parseCommand(AddModuleToStudentTakenCommand.COMMAND_WORD + CODE_DESC_ACC1002);
        assertEquals(new AddModuleToStudentTakenCommand(new ArrayList<>(Arrays.asList(new Code("ACC1002"))),
                new HashSet<>()), command);
    }

    @Test
    public void parseCommand_removeModuleS() throws Exception {
        RemoveModuleFromStudentStagedCommand command = (RemoveModuleFromStudentStagedCommand) parser
                .parseCommand(RemoveModuleFromStudentStagedCommand.COMMAND_WORD + CODE_DESC_ACC1002);
        assertEquals(new RemoveModuleFromStudentStagedCommand(new ArrayList<>(Arrays.asList(new Code("ACC1002"))),
                new HashSet<>()), command);
    }

    @Test
    public void parseCommand_removeModuleT() throws Exception {
        RemoveModuleFromStudentTakenCommand command = (RemoveModuleFromStudentTakenCommand) parser
                .parseCommand(RemoveModuleFromStudentTakenCommand.COMMAND_WORD + CODE_DESC_ACC1002);
        assertEquals(new RemoveModuleFromStudentTakenCommand(new ArrayList<>(Arrays.asList(new Code("ACC1002"))),
                new HashSet<>()), command);
    }

    @Test
    public void parseCommand_search() throws Exception {
        List<String> keywords = Arrays.asList("CS", "AC1");
        SearchCommand command = (SearchCommand) parser.parseCommand(
                SearchCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new SearchCommand(new CodeStartsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_showModule() throws Exception {
        ShowModuleCommand command = (ShowModuleCommand) parser
                .parseCommand(ShowModuleCommand.COMMAND_WORD + CODE_DESC_ACC1002);
        assertEquals(new ShowModuleCommand(new Code("ACC1002")), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor =
            new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command =
            (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD
                + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_generate() throws Exception {
        assertTrue(parser.parseCommand(GenerateCommand.COMMAND_WORD) instanceof GenerateCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }


    @Test
    public void parseCommand_login() throws Exception {
        Credential validCredential = new CredentialBuilder(CREDENTIAL_STUDENT_MAX)
            .withPassword(VALID_PASSWORD)
            .build();
        Path validPath = Paths.get(VALID_PATH);

        // Updates validCredential with hashed password
        validCredential = new CredentialBuilder(validCredential)
            .withPassword(ParserUtil.parsePassword(VALID_PASSWORD).toString())
            .build();

        LoginCommand command =
            (LoginCommand) parser.parseCommand(CredentialUtil.getLoginCommand(validCredential));

        assertEquals(new LoginCommand(validCredential, validPath), command);
    }

    @Test
    public void parseCommand_logout() throws Exception {
        assertTrue(parser.parseCommand(LogoutCommand.COMMAND_WORD) instanceof LogoutCommand);
    }

    @Test
    public void parseCommand_register() throws Exception {
        Credential credential = new CredentialBuilder().build();
        Student student = new StudentBuilder().build();
        RegisterCommand command = (RegisterCommand) parser.parseCommand(
            StudentUtil.getRegisterCommand(student));
        assertEquals(new RegisterCommand(credential, student, PATH_USERDATA_MAX), command);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new SelectCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addAdmin() throws Exception {
        Credential credential = new CredentialBuilder().build();
        Admin admin = new AdminBuilder().build();
        AddAdminCommand command = (AddAdminCommand) parser.parseCommand(AdminUtil.getAddAdminCommand(admin));
        assertEquals(new AddAdminCommand(admin, credential, PATH_USERDATA_SEB), command);
    }

    @Test
    public void parseCommand_removeUser() throws Exception {
        RemoveUserCommand command = (RemoveUserCommand) parser.parseCommand(
                RemoveUserCommand.COMMAND_WORD + " " + VALID_USERNAME);
        assertEquals(new RemoveUserCommand(new Username(VALID_USERNAME)), command);
    }

    @Test
    public void parseCommand_addModuleDb() throws Exception {
        Module module = new ModuleBuilder().build();
        AddModuleToDatabaseCommand command = (AddModuleToDatabaseCommand) parser.parseCommand(
                ModuleUtil.getAddModuleToDatabase(module));
        assertEquals(new AddModuleToDatabaseCommand(module), command);
    }

    @Test
    public void parseCommand_removeModuleDb() throws Exception {
        RemoveModuleFromDatabaseCommand command = (RemoveModuleFromDatabaseCommand) parser.parseCommand(
                RemoveModuleFromDatabaseCommand.COMMAND_WORD + " " + "CS1010");
        assertEquals(new RemoveModuleFromDatabaseCommand("CS1010"), command);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
