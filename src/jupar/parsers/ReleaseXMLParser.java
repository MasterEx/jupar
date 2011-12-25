/**
 * Written by Periklis Master_ex Ntanasis <pntanasis@gmail.com>
 * http://masterex.github.com/
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package jupar.parsers;

import jupar.objects.Modes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import jupar.objects.Release;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Periklis Ntanasis
 */
public class ReleaseXMLParser {

    public Release parse(String filename, Modes mode) throws SAXException, FileNotFoundException, IOException, InterruptedException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        ReleaseXMLParserHandler handler = new ReleaseXMLParserHandler();
        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        if (mode == Modes.FILE) {
            reader.parse(new InputSource(new FileReader(new File(filename))));
        } else {
            java.net.URL u = new java.net.URL(filename);
            java.net.URLConnection conn = u.openConnection();
            java.io.InputStream in = conn.getInputStream();
            reader.parse(new InputSource(in));
        }

        return handler.getInfo();

    }
}
