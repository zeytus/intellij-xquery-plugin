package uk.co.overstory.xquery.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import uk.co.overstory.xquery.psi.XqyQName;
import uk.co.overstory.xquery.psi.XqyRefVarName;

import org.jetbrains.annotations.NotNull;


/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/14/12
 * Time: 5:04 PM
 */
public class XqyVarNameReference extends XqyQNameImpl implements XqyRefVarName
{
	public XqyVarNameReference (ASTNode node)
	{
		super (node);
//System.out.println ("XqyVarNameReference constructor called node=" + node.getText());
	}

	@NotNull
	public XqyQName getQName()
	{
		return this;
	}

	// FIXME: This is the same as for XqyFunctionNameReference, consolidate
	@Override
	public PsiReference getReference()
	{
//System.out.println ("XqyVarNameReference.getReference: " + super.toString() + "/" + super.getText());

		return new XqyReferenceImpl<XqyVarNameReference> (this, TextRange.from (0, getTextLength()))
		{
			@Override
			public PsiElement handleElementRename (String newElementName) throws IncorrectOperationException
			{
//System.out.println ("XqyVarNameReference.handleElementRename: " + super.myElement.getText() + " to " + newElementName);
				myElement.getQName().replace (XqyElementFactory.createQNameFromText (getElement ().getProject (), newElementName));

				return myElement;
			}
		};
	}

	// ------------------------------------------------------------

}
