package main.edu.epam.tariffs.parser;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;

public interface Parser {

    Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException;
}
