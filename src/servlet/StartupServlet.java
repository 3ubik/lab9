package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import entity.Ad;
import entity.AdList;
import entity.UserList;
import helper.AdListHelper;
import helper.UserListHelper;
// ������� ����������� ������������� ��� ������� ����������
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// � ������ ������������� ����� ����������� ����� ��������� ������
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
// ��������� ������ �������������
		UserList userList=UserListHelper.readUserList(getServletContext());
// ��������� ������ ������������� � ��������� ��������
// (��� JSP ��� ������������ ����� applicationContext)
		getServletContext().setAttribute("users", userList);
// ��������� ������ ���������
		AdList adList=AdListHelper.readAdList(getServletContext());
// ��������� ������ ���������� � ��������� ��������
// (��� JSP ��� ������������ ����� applicationContext)
		getServletContext().setAttribute("ads", adList);
		for (Ad ad: adList.getAds()) {
// �.�. � ���������� ���������� ������������ ������ id ������, ��� �������� ��������� ������

			ad.setAuthor(userList.findUser(ad.getAuthorId()));
// ���������������� �������� �������� lastModifiedDate
			ad.setLastModified(ad.getLastModified());
		}
	}
}
