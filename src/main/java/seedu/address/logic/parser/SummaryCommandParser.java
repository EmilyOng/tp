package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.SummaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Date;


/**
 * Parses input arguments and create a new SummaryCommand object
 */
public class SummaryCommandParser implements Parser<SummaryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SummaryCommand
     * object and returns a SummaryCommand object for execution.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SummaryCommand parse(String args) throws ParseException {
        if (args.isEmpty() || args.equals(null)) {
            return new SummaryCommand();
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SummaryCommand.MESSAGE_USAGE));
        }

        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        return new SummaryCommand(date);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
