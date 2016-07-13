/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crespo.fernando.bank_renamer;

import com.sun.jndi.toolkit.url.Uri;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import org.docopt.Docopt;

/**
 *
 * @author fxcrespo
 */
public class Main {

    public static void main(String[] args) {
        //Arrays.asList(args).stream().forEach(x -> System.out.println(x));

        String doc = "usage:\n"
                + "    bank_renamer [<PATH>] [-d|--debug]\n"
                + "\n"
                + "options:\n"
                + "    <PATH>         Path to files[default: \"downloads\"]"
                + "    -d|--debug     debug";

        Docopt docopt = new Docopt(doc);
        Map<String, Object> options = docopt.parse(args);

        Optional<String> path = Optional.ofNullable((String) options.get("<PATH>"));

        Path thePath;

        if (path.isPresent()) {

            //System.getProperties().keySet().forEach(x -> System.err.println(x));
            //String path = (String) options.getOrDefault("<PATH>", "$HOME");
            //System.out.println(options.get("<PATH>"));
            thePath = FileSystems.getDefault().getPath(options.get("<PATH>").toString());
        } else {
            //System.out.println(System.getProperty("user.dir"));
            //System.out.println(System.getProperty("user.home"));

            thePath = FileSystems.getDefault().getPath(System.getProperty("user.home"), "download");
            if (!thePath.toFile().exists()) {
                thePath = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Download");
                if (!thePath.toFile().exists()) {
                    System.err.println("Error: Can't find default downloads/Downloads path, please specify one.");
                    System.exit(1);
                }
            }
        }
        System.out.println(thePath.toAbsolutePath().toString());

    }
}
