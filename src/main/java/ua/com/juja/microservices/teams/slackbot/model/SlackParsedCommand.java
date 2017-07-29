package ua.com.juja.microservices.teams.slackbot.model;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ua.com.juja.microservices.teams.slackbot.exceptions.WrongCommandFormatException;
import ua.com.juja.microservices.teams.slackbot.model.DTO.UserDTO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikolay Horushko
 * @author Ivan Shapovalov
 */
@ToString
@Slf4j
public class SlackParsedCommand {
    private final String fromSlackName;
    private final String text;
    private final Set<UserDTO> users;

    public SlackParsedCommand(String fromSlackName, String text, Set<UserDTO> users) {
        this.fromSlackName = fromSlackName;
        this.text = text;
        this.users = users;
    }

    public String getText() {
        return text;
    }

    public UserDTO getFromUser() {
        List<UserDTO> resultUserList = users.stream()
                .filter(user -> user.getSlack().equals(fromSlackName))
                .collect(Collectors.toList());
        if (resultUserList.size() != 1) {
            throw new IllegalArgumentException("Not one fromSlackName found in users");
        }
        return resultUserList.get(0);
    }

    public Set<UserDTO> getUsers() {
        checkIsTextContainsSlackName();
        log.debug("Found '{}' team members in the text: '{}'", users.size(), text);
        return users;
    }

    public void checkIsTextContainsSlackName() {
        if (users.size() == 0) {
            log.warn("The text: '{}' doesn't contain slack name.");
            throw new WrongCommandFormatException(String.format("The text '%s' doesn't contains slackName", text));
        }
    }
}
