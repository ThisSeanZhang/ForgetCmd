package io.whileaway.forgetcmd.commit.entities;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.commit.response.CmdAddLogBriefResponse;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommandCommit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // commit info
    private Long ccid;
    private Long createTime;
    private CommitStatus status;
    // create info
    private Long creatorId;
    private String whoCreated;
    // command info
    private Long cid;
    private String commandName;
    private String briefDesc;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;

    @Column(columnDefinition="text")
    private String cmdOptions;
    @Column(columnDefinition="text")
    private String cmdParams;

    public CmdAddLogBriefResponse convertBrief() {
        CmdAddLogBriefResponse response = new CmdAddLogBriefResponse();
        response.setRid(ccid);
        response.setCommandName(commandName);
        response.setBriefDesc(briefDesc);
        response.setDescription(description);
        response.setVersion(version);
        response.setPlatform(platform);
        response.setArgNum(argNum);
        response.setWhenDeprecated(description);
        response.setWhenEnable(whenEnable);
        response.setStatus(status);
        return response;
    }

    public CreateCmdRequest createCommandRequest() {
        Command cmd = new Command();
        cmd.setCid(cid);
        cmd.setCommandName(commandName);
        cmd.setBriefDesc(briefDesc);
        cmd.setDescription(description);
        cmd.setVersion(version);
        cmd.setPlatform(platform);
        cmd.setArgNum(argNum);
        cmd.setWhenDeprecated(whenDeprecated);
        cmd.setWhenEnable(whenEnable);
        cmd.setCreatorId(creatorId);
        cmd.setWhoCreated(whoCreated);
        cmd.setStatus(CommandStatus.NORMAL);
        CreateCmdRequest request = new CreateCmdRequest();
        request.setCommand(cmd);
        request.setOptions(cmdOptions);
        request.setParams(cmdParams);
        return request;
    }
}
