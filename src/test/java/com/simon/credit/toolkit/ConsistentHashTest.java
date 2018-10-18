package com.simon.credit.toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.simon.credit.toolkit.hash.MapToolkits;
import com.simon.credit.toolkit.hash.consistent.ServerNode;
import com.simon.credit.toolkit.reflect.DataFetcher;

public class ConsistentHashTest {

	public static void main(String[] args) {
		List<ServerNode> list = new ArrayList<ServerNode>();
		list.add(new ServerNode("aaa", 12345));
		list.add(new ServerNode("bbb", 12346));
		list.add(new ServerNode("ccc", 12347));
		list.add(new ServerNode("ddd", 12348));

		Map<String, ServerNode> map = MapToolkits.parseMap(list, new DataFetcher<ServerNode, String>() {
			@Override
			public String fetch(ServerNode source) {
				return source.getServerNodeName();
			}
		});
		System.out.println(JSON.toJSONString(map));

		Map<Long, ServerNode> m = MapToolkits.parseMap(list, new DataFetcher<ServerNode, Long>() {
			@Override
			public Long fetch(ServerNode source) {
				return source.getServerNodeHash();
			}
		});
		System.out.println(JSON.toJSONString(m));

		Map<String, Integer> map2 = MapToolkits.newHashMap();
		map2.put("111", 111);
		map2.put("222", 222);
		map2.put("333", 333);
		System.out.println(JSON.toJSONString(map2));

		Map<String, Integer> map3 = MapToolkits.threadSafeMap(map2);
		System.out.println(map3.getClass().getName());
		System.out.println(JSON.toJSONString(map3));
	}

}
