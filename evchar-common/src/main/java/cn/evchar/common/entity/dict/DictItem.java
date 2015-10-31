package cn.evchar.common.entity.dict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_dict_item")
public class DictItem extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID 自增长
	 * */
	@Id
	@GeneratedValue
	private Long dictItemId;

	/**
	 * 字典代码
	 * */
	@Column(name = "dict_code")
	private String dictCode;

	/**
	 * 字典项代码
	 * */
	@Column(name = "dict_item_code")
	private String dictItemCode;

	/**
	 * 字典项名称
	 * */
	@Column(name = "dict_item_name")
	private String dictItemName;

	/**
	 * 备注
	 * */
	@Column(name = "dict_item_remark")
	private String dictItemRemark;

	/**
	 * 排序码
	 * */
	@Column(name = "dict_item_sort")
	private Long dictItemSort;

	public Long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(Long dictItemId) {
		this.dictItemId = dictItemId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictItemCode() {
		return dictItemCode;
	}

	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}

	public String getDictItemName() {
		return dictItemName;
	}

	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}

	public String getDictItemRemark() {
		return dictItemRemark;
	}

	public void setDictItemRemark(String dictItemRemark) {
		this.dictItemRemark = dictItemRemark;
	}

	public Long getDictItemSort() {
		return dictItemSort;
	}

	public void setDictItemSort(Long dictItemSort) {
		this.dictItemSort = dictItemSort;
	}

}
