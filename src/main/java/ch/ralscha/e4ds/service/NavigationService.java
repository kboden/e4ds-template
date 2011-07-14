package ch.ralscha.e4ds.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.TREE_LOAD;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

import com.google.common.collect.Lists;

@Service
public class NavigationService {

	@ExtDirectMethod(TREE_LOAD)
	public Node getNavigation() {

		Node root = new Node(0, "root", null, false);

		Node business = new Node(10, "Business", null, false);
		business.setChildren(Lists.newArrayList(new Node(100, "Polling Chart", "pollchart", true)));

		Node admin = new Node(20, "Administration", null, false);
		admin.setChildren(Lists.newArrayList(new Node(200, "Users", "userlist", true)));

		Node system = new Node(30, "System", null, false);
		system.setChildren(Lists.newArrayList(new Node(300, "Log Events", "loggingeventlist", true)));

		root.setChildren(Lists.newArrayList(business, admin, system));

		return root;
	}

	static class Node {
		private int id;
		private String text;
		private String view;
		private boolean leaf;
		private List<Node> children = Collections.emptyList();

		public Node(int id, String text, String view, boolean leaf) {
			this.id = id;
			this.text = text;
			this.view = view;
			this.leaf = leaf;
		}

		public int getId() {
			return id;
		}

		public String getText() {
			return text;
		}

		public String getView() {
			return view;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public List<Node> getChildren() {
			return children;
		}

		public void setChildren(List<Node> children) {
			this.children = children;
		}

	}

}
