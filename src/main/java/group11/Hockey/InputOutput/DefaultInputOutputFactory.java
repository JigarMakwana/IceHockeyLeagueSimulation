package group11.Hockey.InputOutput;

import group11.Hockey.InputOutput.JsonParsing.IParseRootElement;
import group11.Hockey.InputOutput.JsonParsing.ParseRootcoaches;
import group11.Hockey.InputOutput.JsonParsing.ParseRootconferences;
import group11.Hockey.InputOutput.JsonParsing.ParseRootfreeAgents;
import group11.Hockey.InputOutput.JsonParsing.ParseRootgameplayConfig;
import group11.Hockey.InputOutput.JsonParsing.ParseRootgeneralManagers;

public class DefaultInputOutputFactory {
	public static IParseRootElement makeParseRootconferences() {
		return new ParseRootconferences();
	}

	public static IParseRootElement makeParseRootfreeAgents() {
		return new ParseRootfreeAgents();
	}

	public static IParseRootElement makeParseRootgameplayConfig() {
		return new ParseRootgameplayConfig();
	}

	public static IParseRootElement makeParseRootgeneralManagers() {
		return new ParseRootgeneralManagers();
	}

	public static IParseRootElement makeParseRootcoaches() {
		return new ParseRootcoaches();
	}
}
