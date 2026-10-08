package org.example.airlinemanagement.application.commands.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.airlinemanagement.application.commands.create.CreateFlightCommand;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateFlightCommand extends CreateFlightCommand {
}