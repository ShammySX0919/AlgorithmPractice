package leetcode.contest.week35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium609_FindDuplicateFiles {
	private String[] parseFile(String fc) {
		String[] pathAndContent = new String[2];
		pathAndContent[0] = fc.substring(0, fc.indexOf("("));
		pathAndContent[1] = fc.substring(fc.indexOf("(") + 1, fc.indexOf(")"));
		return pathAndContent;
	}

	private String[] parsePath(String p) {
		String[] files = null;
		String[] tokens = p.split(" ");
		String path = tokens[0];
		files = new String[tokens.length - 1];
		for (int i = 1; i < tokens.length; i++) {
			files[i - 1] = path + "/" + tokens[i];
		}
		return files;
	}

	public List<List<String>> findDuplicate(String[] paths) {
		List<List<String>> res = new ArrayList<List<String>>();
		Map<String, List<String>> groups = new HashMap<>();
		// group
		for (String path : paths) {
			String[] files = parsePath(path);
			if (files != null && files.length > 0) {
				// parse out files
				for (String f : files) {
					String[] pc = parseFile(f);

					if (groups.containsKey(pc[1])) {
						groups.get(pc[1]).add(pc[0]);
					} else {
						List<String> l = new ArrayList<>();
						l.add(pc[0]);
						groups.put(pc[1], l);
					}
				}
			}
		}
		// build result
		for (Map.Entry<String, List<String>> e : groups.entrySet()) {
			if (e.getValue().size() > 1) {
				res.add(e.getValue());
			}
		}
		return res;
	}
}
