package cn.evchar.service;

import cn.evchar.common.util.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) {
			try {
//           System.out.print(DateUtils.getHours(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN,"2015-11-09 01:20:00"),DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN,"2015-11-09 07:00:00")));
				Test appTest=new Test();
				double xx=appTest.jisuan(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "2015-11-09 01:20:00"), DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "2015-11-09 20:00:00"));
				System.out.print(xx);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	public  Double jisuan(Date start, Date end) throws Exception {
		List<feiyong> feiyongList=new ArrayList<>();

		feiyong fy = new feiyong();
		fy.setName("电费1");
		fy.setStartDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 00:00:00"));
		fy.setEndDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 08:00:00"));
		fy.setPic(0.33);
		feiyongList.add(fy);
		feiyong fy1 = new feiyong();
		fy1.setName("电费2");
		fy1.setStartDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 08:00:00"));
		fy1.setEndDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 16:00:00"));
		fy1.setPic(0.66);
		feiyongList.add(fy1);
		feiyong fy2 = new feiyong();
		fy2.setName("电费3");
		fy2.setStartDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 16:00:00"));
		fy2.setEndDate(DateUtils.convertString2Date(DateUtils.DEFAILT_DATE_TIME_PATTERN, "0000-00-00 23:59:59"));
		fy2.setPic(1.2);
		feiyongList.add(fy2);
		for (feiyong f:feiyongList){
			if (start.getHours()>=f.getStartDate().getHours()){
				Date endate=new Date();
				endate.setTime(start.getTime());
				endate.setHours(f.getEndDate().getHours());
				endate.setMinutes(f.getEndDate().getMinutes());
				f.setYongshi(DateUtils.getHours(start, endate));
				start.setHours(f.getEndDate().getHours());
				start.setMinutes(f.getEndDate().getMinutes());
			}
		}
		double res=0.00;
		for (feiyong fc:feiyongList){
			if (fc.getYongshi()!=null){
				System.out.println("时段"+fc.getName()+"用了："+fc.getYongshi()==null?0:fc.getYongshi()+"此时段总价为："+fc.getZj());
				res=res+(fc.getYongshi()==null?0:fc.getYongshi());
			}else{
				System.out.println("时段"+fc.getName()+"用了："+0);
			}
		}
		return res;
	}
}

class feiyong{
	private String name;
	private Date startDate;
	private Date endDate;
	private Double yongshi;
	private Double pic;
	private Double zj;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getYongshi() {
		return yongshi;
	}

	public void setYongshi(Double yongshi) {
		this.yongshi = yongshi;
	}

	public Double getPic() {
		return pic;
	}

	public void setPic(Double pic) {
		this.pic = pic;
	}

	public Double getZj() {
		return this.getYongshi()*this.getPic();
	}


}