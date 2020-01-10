package com.modules.bootapplication.common.vo;

import java.util.List;

/**
 * bootstrap tree view数据模型
 * @author wcf
 * @company jszb
 * @date 2016年3月14日
 * @version V1.0
 */
public class TreeView {

	private String text;//文本显示内容
	private Integer value;//文本对应的value值
	private String color;//文本颜色
	private String href;//连接地址
	private String[] tags;//目标节点包含的子节点的数量
	private State state;

	private List<TreeView> nodes;


	public List<TreeView> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeView> nodes) {
		this.nodes = nodes;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	/**
	 * 节点状态
	 * @author wcf
	 * @company jszb
	 * @date 2016年3月14日
	 * @version V1.0
	 */
	class State{
		private boolean checked = false;

		private boolean disabled = false;

		private boolean expanded = false;

		private boolean selected = false;

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isExpanded() {
			return expanded;
		}

		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}
}
