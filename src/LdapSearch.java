import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to perform a search by specifying a set of attributes to be
 * matched. Returns selected attributes of objects that contain those matching
 * attributes.
 * 
 * usage: java Search
 */
class LdapSearch {
	public static void printSearchEnumeration(NamingEnumeration retEnum) {
		try {
			while (retEnum.hasMore()) {
				SearchResult sr = (SearchResult) retEnum.next();
				System.out.println(">>>" + sr.getName());
				GetAllAttrs.printAttrs(sr.getAttributes());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
//		env.put(Context.PROVIDER_URL, "ldap://10.64.6.228:8904");
		env.put(Context.PROVIDER_URL, "ldap://10.64.7.11:6389");
		env.put(Context.SECURITY_PRINCIPAL, "uid=rguser");
		env.put(Context.SECURITY_CREDENTIALS, "rguser");

		try {
			// Create initial context
			DirContext ctx = new InitialDirContext(env);

			// Create the default search controls
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// Specify the search filter to match
			String filter = "(objectclass=*)";

			// Search for objects using the filter
			NamingEnumeration answer = ctx
					.search("dc=getresource,msisdn=494500100002,dc=ui,dc=upg,dc=ericsson,dc=com",
							filter, ctls);

			// Print the answer
			printSearchEnumeration(answer);

			// Close the context when we're done
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
