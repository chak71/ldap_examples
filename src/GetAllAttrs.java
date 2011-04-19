import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to retrieve selected attributes of a named object.
 *
 * usage: java GetAttrs
 */
class GetAllAttrs {

    static void printAttrs(Attributes attrs) {
        if (attrs == null) {
            System.out.println("No attributes");
        } else {
            /* Print each attribute */
            try {
                for (NamingEnumeration ae = attrs.getAll();
                     ae.hasMore();) {
                    Attribute attr = (Attribute)ae.next();
                    System.out.println("attribute: " + attr.getID());

                    /* print each value */
                    for (NamingEnumeration e = attr.getAll();
                         e.hasMore();
                         System.out.println("value: " + e.next()))
                        ;
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

}
