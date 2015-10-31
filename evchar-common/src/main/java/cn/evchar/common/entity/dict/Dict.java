package cn.evchar.common.entity.dict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.evchar.common.entity.AbstractEntity;

@Entity
@Table(name = "evchar_dict")
public class Dict extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 * */
	@Id
	@GeneratedValue
	private Long dictId;

	/**
	 * 字典代码
	 * */
	@Column(name = "dict_code")
	private String dictCode;

	/**
	 * 字典代码名称
	 * */
	@Column(name = "dict_name")
	private String dictName;

	/**
	 * 备注
	 * */
	@Column(name = "dict_remark")
	private String dictRemark;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictRemark() {
		return dictRemark;
	}

	public void setDictRemark(String dictRemark) {
		this.dictRemark = dictRemark;
	}

}
