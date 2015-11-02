
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<A,B> extends LinkedHashMap<A,B>{
	private int numEntries = 10;
	//------------------------------------------------------------------------------
	public LruCache(int numEntries){
		super(numEntries + 1, 1.0f, true);
		this.numEntries = numEntries;
	}
	//------------------------------------------------------------------------------
	@Override
    protected boolean removeEldestEntry(final Map.Entry<A, B> eldest) {
        return super.size() > numEntries;
    }
	//------------------------------------------------------------------------------
}
