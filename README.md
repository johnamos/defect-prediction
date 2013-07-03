defect-prediction
=================

Sonar plugin that predicts which source files contain the most defects.

Currently supports Git and CVS.  You can add support for another version
control system by adding a new class that implements ScmLogParser and then
adding your new class to ScmLogParserFactory.

Please read the project wiki for more information:
https://github.com/johnamos/defect-prediction/wiki
