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

import jupar.objects.Release;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Periklis Ntanasis
 */
public class ReleaseXMLParserHandler extends DefaultHandler {

    private String currentelement = "";
    private Release releaseinfo = new Release();

    public ReleaseXMLParserHandler() {
        super();
    }

    @Override
    public void startElement(String uri, String name,
            String qName, Attributes atts) {
        currentelement = qName;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String value = null;
        if (!currentelement.equals("")) {
            value = String.copyValueOf(ch, start, length).trim();
        }
        if (currentelement.equals("pubDate")) {
            releaseinfo.setPubDate(value);
        } else if (currentelement.equals("pkgver")) {
            releaseinfo.setpkgver(value);
        } else if (currentelement.equals("pkgrel")) {
            releaseinfo.setPkgrel(value);
        } else if (currentelement.equals("severity")) {
            releaseinfo.setseverity(value);
        } else if (currentelement.equals("message")) {
            releaseinfo.setMessage(value);
        }
        currentelement = "";
    }

    public Release getInfo() {
        return releaseinfo;
    }
}
