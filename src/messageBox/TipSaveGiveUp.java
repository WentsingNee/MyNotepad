package messageBox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class TipSaveGiveUp {

	protected boolean result;
	protected Shell shell;
	protected Shell parent;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TipSaveGiveUp(Shell parent) {
		this.parent = parent;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public void open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public boolean getResult() {
		return result;
	}

	public Shell getParent() {
		return parent;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell();
		shell.setSize(466, 218);
		shell.setText("");
		shell.setLayout(new GridLayout(1, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("New Label");
		new Label(shell, SWT.NONE);

		Composite composite = new Composite(shell, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 469;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(9, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button notSaveButton = new Button(composite, SWT.NONE);
		notSaveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = true;
				shell.close();
			}
		});
		GridData gd_notSaveButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_notSaveButton.widthHint = 110;
		notSaveButton.setLayoutData(gd_notSaveButton);
		notSaveButton.setText("确定不保存");
		new Label(composite, SWT.NONE);

		Button backToEditButton = new Button(composite, SWT.NONE);
		backToEditButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = false;
				shell.close();
			}
		});
		GridData gd_backToEditButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_backToEditButton.widthHint = 98;
		backToEditButton.setLayoutData(gd_backToEditButton);
		backToEditButton.setText("我再想想");

	}

}
