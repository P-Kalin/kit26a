# �4 ����: ����������� �������� �������� ��� ��������� Java SE.<br/>����: �������� ������������� ���������� ������� ����� Java. ��������� ���������� ������ ������ � ������������ � ���������� ���������.

# 1 ������������ ��������

## 1.1 ���������

������� ������� ������ ����������, ʲ�-26�, ������ 8 (�������� �8).

## 1.2 ������

1. �������������� �������� ������ �������� ����������� ������ �3, �������� �� ��������� ������ ����������� ������� ������ ����������� � ������ ���������� ����:  
    - �������� �����;
    - �������� �����;
    - ��������� ���������;
    - ����������� ����������;
    - ���������� �������� � �.�.
    
2. ����������� ������� ��������� ���������� ����� ��� ���������� ������ ������ ��������: ?�������� "-h" �� "-help":
    - ������������ ���������� ��� ������ ��������, ����������� (������������ ��������), ��������� ���� ������ ������ (������ ���� �� ��������� ���������� �����);
    - �������� "-d" �� "-debug": � ������ ������ �������� ������������� �������� ���, �� ���������� ������������ �� �������� ������������� ��������: ���������� �����������, ������ �������� ������, �������� ���������� ������ �� ��.

## 1.3 ��������

������ �����. � ����� ������ �� ���� ���, � ���� ���� � ����� (������ �������) ������ (���������: "abc"-"cba", "def"-"fed"). ��������� ������� � ������ �������.

# 2 �������� ��������

## 2.1 ������ ���

ϳ� ��� ������� ����������� ������ ���� ����������� ������� Command ��� ������������ �������� �� �������� ������ �� ����� Decorator ��� ���������� ����������� ������ ��� ������� �� ������������ ��������� �������� ���������� ��� �������� �������. ��� ���� ������� ��������� ������� ������ ����������� ��������.

## 2.2 �������� �� ��������� �����

������ ������ ������� ������:
- shell - ������ ������������ �������� �� �� ������� �����.
- �ommand - ������ � ��� ������ ������, �� ������� ��� �� ���������.

<center>
  <img src="http://i.piccy.info/i9/26ff43650dcc9fb4db05ce9a46e9125a/1506440422/131021/1182944/ua_khpi_oop_malokhvii04_shell.png" alt="ĳ������� ����� �� ������ shell">
  <p>���. 1 - ĳ������� ����� �� ������ shell</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/3ae492cdf72d77851489eb7d7527789c/1506440464/103808/1182944/ua_khpi_oop_malokhvii04_shell_command.png" alt="ĳ������� ����� �� ������ command">
  <p>���. 2 - ĳ������� ����� �� ������ command</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/91acd094787974cecc0722a007120848/1506440496/133723/1182944/ua_khpi_oop_malokhvii04.png" alt="�������� ������� �����">
  <p>���. 3 - �������� ������� �����</p>
</center>

## 2.3 ���� ��������

����� �������� ���������� ������� ��������� ��������� ����� ��������� �����, ���������� ������ ��� ��� ������, ���������� ���������� ������ ������.

## 2.4 ������ ��������� ��������

����� �������� �������� ����� ����� �������� (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii04/>).

```
package ua.khpi.oop.malokhvii04;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import ua.khpi.oop.malokhvii03.text.AnanymsCollection;
import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.CommandLineParser;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;
import ua.khpi.oop.malokhvii04.shell.command.Command;
import ua.khpi.oop.malokhvii04.shell.command.CommandFactory;

public final class Application {

    public static void main(final String[] args) throws IOException {
        WordsCollection wordsCollection = new WordsCollection();
        AnanymsCollection ananymsCollection = new AnanymsCollection();

        ShellData shellData = ShellData.getBuilder()
                .setWordsCollection(wordsCollection)
                .setAnanymsCollection(ananymsCollection)
                .setCommandHistorySize(
                        CommandLineParser.parseCommandHistorySize(args))
                .setCommandCharacter(
                        CommandLineParser.parseCommandCharacter(args))
                .setTabCharacter(CommandLineParser.parseTabCharacter(args))
                .setInputStream(System.in).build();

        Shell shell = new Shell(shellData);
        shell.launch();

        for (Command command : CommandLineParser.parseCommands(args,
                shellData)) {
            shell.putCommand(command);
            shell.handleCommand();
        }

        while (shell.isRunning()) {
            Command command = CommandFactory.getCommand(shell.getNextCommand(),
                    shell.getShellData());
            if (command != null) {
                shell.putCommand(command);
                shell.handleCommand();
            } else {
                shell.handleUnknownCommand();
            }
        }
        shell.terminate();
    }

    public static String getApplicationName() {
        File file = null;
        try {
            file = new File(Application.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
        } catch (URISyntaxException exception) {

        }
        return file.getName();
    }
}

```

# 3 ���������� ������

����� �������� ��������� ��������� � ������ ������������ ������.

<center>
  <img src="http://i.piccy.info/i9/e51373d10d5bf4a2511412a440e15d27/1506438623/40885/1182944/application_1.png" alt="�������� �������������� ��������">
  <p>���. 3 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/dddc588590c122c386b912ed81815c04/1506438823/45450/1182944/application_2.png" alt="�������� �������������� ��������">
  <p>���. 4 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/91a6c5b14357d78f73a6631ad38401b7/1506438848/30043/1182944/application_3.png" alt="�������� �������������� ��������">
  <p>���. 5 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/08c3f25d6e0531452a6f277fdd90303f/1506438986/50995/1182944/application_4.png" alt="�������� �������������� ��������">
  <p>���. 6 - �������� �������������� ��������</p>
  
  <img src="http://i.piccy.info/i9/e027831a9e13b040b9bbdb03c12f5ec2/1506440891/52164/1182944/application_5.png" alt="�������� �������������� ��������">
  <p>���. 7 - �������� �������������� ��������</p>
</center>

# ��������

� ��� ��������� ����������� ������ ���� �������� ������� �������� ������������� ���������� ������� ����� Java. ���������� ��������� ����� ������ � ������������ � ��������� �������.