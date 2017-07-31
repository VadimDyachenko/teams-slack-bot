package ua.com.juja.microservices.teams.slackbot.service;

import me.ramswaroop.jbot.core.slack.models.RichMessage;
import org.springframework.stereotype.Service;
import ua.com.juja.microservices.teams.slackbot.model.Team;
import ua.com.juja.microservices.teams.slackbot.model.TeamRequest;

/**
 * @author Ivan Shapovalov
 */
@Service
public interface TeamSlackbotService {

    RichMessage activateTeam(String text);

}
