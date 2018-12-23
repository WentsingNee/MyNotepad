package usrManager;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Window.UsrMainPage;
import messageBox.LoginInfo;

public class LoginWindow {
	private Text password;
	private Button rememberPassword;
	private Label lblUser;
	private Label lblPassword;
	private CCombo user_name;
	private Button button;
	private Shell shell;
	private Composite composite;

	/**
	 * @wbp.parser.entryPoint
	 */
	public LoginWindow() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginWindow.class,
				"/org/eclipse/jface/fieldassist/images/info_ovr@2x.png"));
		shell.setSize(609, 411);
		shell.setText("登录");
		shell.setLocation(500, 200);
		setScreenMid();
		createContents();

	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	void createContents() {
		shell.setLayout(new FormLayout());

		rememberPassword = new Button(shell, SWT.CHECK);
		FormData fd_rememberPassword = new FormData();
		fd_rememberPassword.bottom = new FormAttachment(0, 241);
		fd_rememberPassword.right = new FormAttachment(0, 363);
		fd_rememberPassword.top = new FormAttachment(0, 211);
		fd_rememberPassword.left = new FormAttachment(0, 232);
		rememberPassword.setLayoutData(fd_rememberPassword);
		rememberPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UsrManagerHelper.setRememberPassword(user_name.getText(), rememberPassword.getSelection());
			}
		});

		button = new Button(shell, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.bottom = new FormAttachment(0, 326);
		fd_button.right = new FormAttachment(0, 350);
		fd_button.top = new FormAttachment(0, 279);
		fd_button.left = new FormAttachment(0, 240);
		button.setLayoutData(fd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (UsrManagerHelper.chkUsrExist(user_name.getText()) == false) {
					new LoginInfo(shell, SWT.DIALOG_TRIM, "登录失败", "用户名不存在").open();
				} else if (UsrManagerHelper.chkPassword(user_name.getText(), password.getText()) == false) {
					new LoginInfo(shell, SWT.DIALOG_TRIM, "登录失败", "密码不正确").open();
				} else {
					shell.setVisible(false);
					new UsrMainPage(user_name.getText()).open();
					shell.close();
				}
			}
		});
		button.setText("登录");
		rememberPassword.setText("  记住密码");

		composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(6, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(button, -249);
		fd_composite.bottom = new FormAttachment(rememberPassword, -32);
		fd_composite.right = new FormAttachment(100, -110);
		fd_composite.left = new FormAttachment(0, 115);
		composite.setLayoutData(fd_composite);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		lblUser = new Label(composite, SWT.CENTER);
		lblUser.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblUser.setText("用户名");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		user_name = new CCombo(composite, SWT.BORDER);
		GridData gd_user_name = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_user_name.widthHint = 149;
		user_name.setLayoutData(gd_user_name);
		user_name.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				password.setText("");

				if (user_name.getSelectionIndex() == user_name.getItems().length - 1) {
					AddNewUsrWindow add_new_user = new AddNewUsrWindow(shell);
					add_new_user.open();

					user_name.removeAll();
					Set<String> usr_name_list = UsrManagerHelper.getAllUsrList();
					for (String name : usr_name_list) {
						user_name.add(name);
					}
					user_name.add("添加新的用户");
				}

				if (user_name.getSelectionIndex() != user_name.getItemCount() - 1
						&& user_name.getText().length() != 0) {
					rememberPassword.setSelection(UsrManagerHelper.isRememberPassword(user_name.getText()));
				}
			}
		});
		updateUsr();

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		lblPassword = new Label(composite, SWT.CENTER);
		lblPassword.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		lblPassword.setText("密码");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		password = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_password = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_password.widthHint = 142;
		password.setLayoutData(gd_password);

		if (user_name.getSelectionIndex() != user_name.getItemCount() - 1 && user_name.getText().length() != 0) {
			rememberPassword.setSelection(UsrManagerHelper.isRememberPassword(user_name.getText()));
		}

		if (rememberPassword.getSelection() == true) {
			// password.setText(UsrManagerHelper);
		}
	}

	void updateUsr() {
		user_name.removeAll();
		Set<String> usr_name_list = UsrManagerHelper.getAllUsrList();
		for (String name : usr_name_list) {
			user_name.add(name);
		}
		user_name.add("添加新的用户");
		if (user_name.getItems().length >= 1) {
			user_name.select(0);
		}

	}

	public void setScreenMid() {
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
	}
}
