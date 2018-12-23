package usrManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import messageBox.LoginInfo;

public class AddNewUsrWindow {
	private Text new_user_name;
	private Text password;
	private Text confirm_password;
	private Shell shell;
	private Shell parent;

	AddNewUsrWindow(Shell parent) {
		this.parent = parent;
	}

	void setMidden(int thisWidth, int thisHeight) {
		Shell parent = getParent();
		shell.setSize(thisWidth, thisHeight);
		shell.setLocation(parent.getLocation().x + (parent.getSize().x - thisWidth) / 2,
				parent.getLocation().y + (parent.getSize().y - thisHeight) / 2);
	}

	private Shell getParent() {
		return parent;
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell();
		setMidden(465, 346);
		shell.setText("添加新用户");
		shell.setLayout(null);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(92, 58, 60, 20);
		lblNewLabel.setText("新用户名");

		new_user_name = new Text(shell, SWT.BORDER);
		new_user_name.setBounds(238, 58, 146, 26);

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(92, 114, 30, 20);
		lblNewLabel_1.setText("密码");

		password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		password.setBounds(238, 114, 146, 26);

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(92, 170, 60, 20);
		lblNewLabel_2.setText("确认密码");

		confirm_password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		confirm_password.setBounds(238, 170, 146, 26);

		Button add_new_user_button = new Button(shell, SWT.NONE);
		add_new_user_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				if (new_user_name.getText().length() == 0) {
					new LoginInfo(shell, 0, "新用户名不可为空", "新用户名不可为空").open();
				} else if (password.getText().length() == 0) {
					new LoginInfo(shell, 0, "密码不可为空", "密码不可为空").open();
				} else if (confirm_password.getText().equals(password.getText()) == false) {
					new LoginInfo(shell, 0, "确认密码不一致", "确认密码不一致").open();
				} else if (UsrManagerHelper.chkUsrExist(new_user_name.getText()) == true) {
					new LoginInfo(shell, 0, "用户名已存在", "用户名已存在").open();
				} else {
					if (UsrManagerHelper.addUser(new_user_name.getText(), password.getText()) == true) {
						new LoginInfo(shell, 0, "添加成功", "添加成功").open();
						shell.close();
					} else {
						new LoginInfo(shell, 0, "添加失败", "添加失败").open();
					}
				}
			}
		});

		add_new_user_button.setBounds(160, 242, 83, 30);
		add_new_user_button.setText("添加");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
